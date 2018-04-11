import groovy.transform.Field
import org.parosproxy.paros.Constant
import org.parosproxy.paros.network.HttpMessage
import org.zaproxy.zap.extension.script.ExtensionScript
import org.zaproxy.zap.extension.script.HttpSenderScriptHelper

import java.nio.file.Paths

@Field final def wrappedScript = debug()

def debug() {
    def relativeScriptFilePath = "scripts/httpsender/HttpSenderDefaultTemplate.groovy"

    def scriptFilePath = Paths
            .get(Constant.getZapHome(), ExtensionScript.SCRIPTS_DIR, relativeScriptFilePath)
            .toAbsolutePath()
            .toString()

    def script = new File(scriptFilePath)
    def scriptFunctions = evaluate(script)
    return scriptFunctions
}

void sendingRequest(HttpMessage msg, int initiator, HttpSenderScriptHelper helper){
    println("Start Debugging...")
    wrappedScript.sendingRequest(msg, initiator, helper)
    println("End Debugging...")
}

void responseReceived(HttpMessage msg, int initiator, HttpSenderScriptHelper helper) {
    println("Start Debugging...")
    wrappedScript.responseReceived(msg, initiator, helper)
    println("End Debugging...")
}
