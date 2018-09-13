import groovy.transform.Field
import org.parosproxy.paros.Constant
import org.zaproxy.zap.extension.script.ExtensionScript
import org.zaproxy.zap.extension.websocket.WebSocketMessage
import org.zaproxy.zap.extension.websocket.WebSocketSenderScriptHelper

import java.nio.file.Paths

@Field final def wrappedScript = debug()

def debug() {
    def relativeScriptFilePath = "scripts/websocketsender/WebsockerSenderDefaultTemplate.groovy"

    def scriptFilePath = Paths
            .get(Constant.getZapHome(), ExtensionScript.SCRIPTS_DIR, relativeScriptFilePath)
            .toAbsolutePath()
            .toString()

    def script = new File(scriptFilePath)
    def scriptFunctions = evaluate(script)
	if(scriptFunctions == null){
        print "Do not forget to add the DebugFooter into the Script. 'return [...]'"
    }
    return scriptFunctions
}

void onMessageFrame(WebSocketMessage msg, WebSocketSenderScriptHelper helper){
    println("Start Debugging...")
    wrappedScript.onMessageFrame(msg, helper)
    println("End Debugging...")
}

