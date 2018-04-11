import groovy.transform.Field
import org.parosproxy.paros.Constant
import org.zaproxy.zap.extension.script.ExtensionScript

import java.nio.file.Paths

@Field final def wrappedScript = debug()

def debug() {
    def relativeScriptFilePath = "scripts/payloadprocessor/PayloadProcessorDefaultTemplate.groovy"

    def scriptFilePath = Paths
            .get(Constant.getZapHome(), ExtensionScript.SCRIPTS_DIR, relativeScriptFilePath)
            .toAbsolutePath()
            .toString()

    def script = new File(scriptFilePath)
    def scriptFunctions = evaluate(script)
    return scriptFunctions
}

String process(String payloadValue) {
    println("Start Debugging...")
    def result = wrappedScript.process(payloadValue)
    println("End Debugging...")
    return result
}