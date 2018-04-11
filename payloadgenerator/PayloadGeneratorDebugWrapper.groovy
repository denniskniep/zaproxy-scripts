import groovy.transform.Field
import org.parosproxy.paros.Constant
import org.zaproxy.zap.extension.script.ExtensionScript

import java.nio.file.Paths

@Field final def wrappedScript = debug()

def debug() {
    def relativeScriptFilePath = "scripts/payloadgenerator/PayloadGeneratorDefaultTemplate.groovy"

    def scriptFilePath = Paths
            .get(Constant.getZapHome(), ExtensionScript.SCRIPTS_DIR, relativeScriptFilePath)
            .toAbsolutePath()
            .toString()

    def script = new File(scriptFilePath)
    def scriptFunctions = evaluate(script)
    return scriptFunctions
}

long getNumberOfPayloads() {
    println("Start Debugging...")
    def result = wrappedScript.getNumberOfPayloads()
    println("End Debugging...")
    return result
}

boolean hasNext() {
    println("Start Debugging...")
    def result = wrappedScript.hasNext()
    println("End Debugging...")
    return result
}

String next() {
    println("Start Debugging...")
    def result = wrappedScript.next()
    println("End Debugging...")
    return result
}

void reset() {
    println("Start Debugging...")
    wrappedScript.reset()
    println("End Debugging...")
}

void close() {
    println("Start Debugging...")
    wrappedScript.close()
    println("End Debugging...")
}
