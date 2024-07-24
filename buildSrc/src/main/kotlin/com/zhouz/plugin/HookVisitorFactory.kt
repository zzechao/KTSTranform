package com.zhouz.plugin

import com.android.build.api.instrumentation.AsmClassVisitorFactory
import com.android.build.api.instrumentation.ClassContext
import com.android.build.api.instrumentation.ClassData
import org.gradle.api.Incubating
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes


/**
 * @author:zhouz
 * @date: 2024/7/24 17:31
 * description：
 */
@Incubating
abstract class HookVisitorFactory : AsmClassVisitorFactory<HookParameters> {

    override fun createClassVisitor(classContext: ClassContext, nextClassVisitor: ClassVisitor): ClassVisitor {
        return HookClassVisitor(nextClassVisitor)
    }

    override fun isInstrumentable(classData: ClassData): Boolean {
        Logger.i("isInstrumentable: ${classData.className}")
        return classData.className == parameters.get().hookClass
    }

    inner class HookClassVisitor(classVisitor: ClassVisitor) : ClassVisitor(Opcodes.ASM9, classVisitor) {
        override fun visitMethod(access: Int, name: String?, descriptor: String?, signature: String?, exceptions: Array<out String>?): MethodVisitor {
            var mv = super.visitMethod(access, name, descriptor, signature, exceptions)
            if (name == parameters.get().hookMethod) {
                mv = HookClassMethod(mv)
            }
            return mv
        }
    }

    inner class HookClassMethod(mv: MethodVisitor) : MethodVisitor(Opcodes.ASM9, mv) {
        override fun visitInsn(opcode: Int) {
            super.visitInsn(opcode)
            Logger.i("HookClassMethod visitInsn $opcode")
        }
    }
}