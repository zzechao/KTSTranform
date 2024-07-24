package run


import org.objectweb.asm.ClassReader
import org.objectweb.asm.util.ASMifier
import org.objectweb.asm.util.Textifier
import org.objectweb.asm.util.TraceClassVisitor
import java.io.PrintWriter


/**
 * @author:zhouz
 * @date: 2024/6/28 10:58
 * description：输出asm
 */
fun main(args: Array<String>) {
    val clazzName = "sample.TestClass2"
    val parsingOption = ClassReader.SKIP_FRAMES or ClassReader.SKIP_DEBUG
    val asmCode = true

    val printer = if (asmCode) {
        ASMifier()
    } else {
        Textifier()
    }
    val printWriter = PrintWriter(System.out, true)
    val traceClassVisitor = TraceClassVisitor(null, printer, printWriter)
    ClassReader(clazzName).accept(traceClassVisitor, parsingOption)
}