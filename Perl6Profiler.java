/* BTrace Script Template */
import com.sun.btrace.annotations.*;
import com.sun.btrace.BTraceUtils;
import com.sun.btrace.Profiler;

@BTrace
public class Perl6Profiler {
    @Property
    public static Profiler profiler = BTraceUtils.Profiling.newProfiler();
    
    @OnMethod(clazz="/org\\.perl6\\..*/", method="/.*/")
    public static void entry(@ProbeMethodName(fqn=true) String probeMethod) { 
        BTraceUtils.Profiling.recordEntry(profiler, probeMethod);
    }
    
    @OnMethod(clazz="/org\\.perl6\\..*/", method="/.*/", location=@Location(value=Kind.RETURN))
    public static void exit(@ProbeMethodName(fqn=true) String probeMethod, @Duration long duration) { 
        BTraceUtils.Profiling.recordExit(profiler, probeMethod, duration);
    }
    
    @OnTimer(10000)
    public static void report() {
        BTraceUtils.Profiling.printSnapshot("Performance profile", profiler);
    }
}
