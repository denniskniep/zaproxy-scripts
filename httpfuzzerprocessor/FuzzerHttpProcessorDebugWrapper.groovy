import groovy.transform.Field
import org.parosproxy.paros.Constant
import org.parosproxy.paros.network.HttpMessage
import org.zaproxy.zap.extension.fuzz.httpfuzzer.HttpFuzzResult
import org.zaproxy.zap.extension.fuzz.httpfuzzer.HttpFuzzerTaskProcessorUtils
import org.zaproxy.zap.extension.script.ExtensionScript

import java.nio.file.Paths

@Field final def wrappedScript = debug()

def debug() {
    def relativeScriptFilePath = "scripts/httpfuzzerprocessor/FuzzerHttpProcessorDefaultTemplate.groovy"

    def scriptFilePath = Paths
            .get(Constant.getZapHome(), ExtensionScript.SCRIPTS_DIR, relativeScriptFilePath)
            .toAbsolutePath()
            .toString()

    def script = new File(scriptFilePath)
    def scriptFunctions = evaluate(script)
    return scriptFunctions
}

void processMessage(HttpFuzzerTaskProcessorUtils utils, HttpMessage message) {
    println("Start Debugging...")
    wrappedScript.processMessage(utils, message)
    println("End Debugging...")
}

boolean processResult(HttpFuzzerTaskProcessorUtils utils, HttpFuzzResult fuzzResult) {
    println("Start Debugging...")
    def result = wrappedScript.processResult(utils, fuzzResult)
    println("End Debugging...")
    return result
}
