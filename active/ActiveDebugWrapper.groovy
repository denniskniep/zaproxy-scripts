import groovy.transform.Field
import org.parosproxy.paros.Constant
import org.parosproxy.paros.network.HttpMessage
import org.zaproxy.zap.extension.ascan.ScriptsActiveScanner
import org.zaproxy.zap.extension.script.ExtensionScript

import java.nio.file.Paths

@Field final def wrappedScript = debug()

def debug() {
    def relativeScriptFilePath = "scripts/active/ActiveDefaultTemplate.groovy"

    def scriptFilePath = Paths
            .get(Constant.getZapHome(), ExtensionScript.SCRIPTS_DIR, relativeScriptFilePath)
            .toAbsolutePath()
            .toString()

    def script = new File(scriptFilePath)
    def scriptFunctions = evaluate(script)
    return scriptFunctions
}

void scanNode(ScriptsActiveScanner aScan, HttpMessage msg) {
    println("Start Debugging...")
    wrappedScript.scanNode(aScan, msg)
    println("End Debugging...")
}

void scan(ScriptsActiveScanner aScan, HttpMessage msg, String param, String value) {
    println("Start Debugging...")
    wrappedScript.scan(aScan, msg, param, value)
    println("End Debugging...")
}



