package pl.jaca.jbo.transform.optimizers.redundantremover;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import pl.jaca.jbo.report.Reporter;
import pl.jaca.jbo.transform.TransformationReport;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 * @author Jaca777
 *         Created 2016-02-26 at 18
 */
public class CallableRedundantRemover implements Callable<ClassNode> {

    public static final String RESOLVING_TRANSFORM = "Redundant methods removed";

    private MethodsResolver classVisitor;
    private ClassNode classNode;

    public CallableRedundantRemover(ClassNode classNode) {
        this.classNode = classNode;
        this.classVisitor = new MethodsResolver(Opcodes.ASM5);
    }

    @Override
    public ClassNode call() throws Exception {
        classNode.accept(classVisitor);
        Set<String> redundantMethods = this.classVisitor.getRedundantMethods();
        MethodsRemover methodsRemover = new MethodsRemover(Opcodes.ASM5, redundantMethods);
        classNode.accept(methodsRemover);
        return methodsRemover;
    }


    public ClassNode getClassNode() {
        return classNode;
    }

    public String getName() {
        return classVisitor.getName();
    }

}