package com.zhouz.buildsrc

import com.android.build.api.instrumentation.AsmClassVisitorFactory
import com.android.build.api.instrumentation.ClassContext
import com.android.build.api.instrumentation.ClassData
import com.android.build.api.instrumentation.InstrumentationContext
import org.gradle.api.Incubating
import org.gradle.api.provider.Property
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.tree.AbstractInsnNode
import org.objectweb.asm.tree.ClassNode
import org.objectweb.asm.tree.InsnList
import org.objectweb.asm.tree.MethodInsnNode
import org.objectweb.asm.tree.TypeInsnNode
import org.objectweb.asm.tree.VarInsnNode


/**
 * @author:zhouz
 * @date: 2024/4/22 14:38
 * description：TODO
 */
@Incubating
abstract class GsonClassVisitorFactory : AsmClassVisitorFactory<GsonIConfig> {


    override fun createClassVisitor(classContext: ClassContext, nextClassVisitor: ClassVisitor): ClassVisitor {
        return ThreadClassVisitor(parameters.get(), classContext, nextClassVisitor)
    }

    override fun isInstrumentable(classData: ClassData): Boolean {
        return parameters.get().hookClass == classData.className
    }

    private class ThreadClassVisitor(
        val config: GsonIConfig, val classContext: ClassContext, val nextClassVisitor:
        ClassVisitor
    ) : ClassNode(Opcodes.ASM5) {
        override fun visitEnd() {
            super.visitEnd()
            methods.firstOrNull {
                it.name == config.hookMethod
            }?.let { method ->
                val removeList = mutableListOf<AbstractInsnNode>()
                var needInser = false
                method.instructions.iterator().forEach { insn ->
                    if (needInser) {
                        removeList.add(insn)
                    }
                    if (insn.opcode == Opcodes.NEW &&
                        insn is TypeInsnNode &&
                        insn.desc == "java/lang/IllegalStateException"
                    ) {
                        needInser = true
                        removeList.add(insn)
                    } else if (needInser && insn.opcode == Opcodes.ATHROW) {
                        needInser = false
                    }
                }
                if (removeList.isNotEmpty()) {
                    val insnList = InsnList()
                    insnList.add(VarInsnNode(Opcodes.ALOAD, 0))
                    insnList.add(
                        MethodInsnNode(
                            Opcodes.INVOKESTATIC, config.fixClass.replace(".", "/"),
                            "fixGsonBug", "(Lcom/google/gson/stream/JsonReader;)Ljava/lang/String;"
                        )
                    )
                    insnList.add(VarInsnNode(Opcodes.ASTORE, 2))
                    method.instructions.insertBefore(removeList[0], insnList)
                    removeList.forEach { item ->
                        method.instructions.remove(item)
                    }
                }
            }

            accept(nextClassVisitor)
        }
    }
}

class GsonClassVisitorFactoryImpl(
    override val instrumentationContext: InstrumentationContext,
    override val parameters: Property<GsonIConfig>
) : GsonClassVisitorFactory() {

}