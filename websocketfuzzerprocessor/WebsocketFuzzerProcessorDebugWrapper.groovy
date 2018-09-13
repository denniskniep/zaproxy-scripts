import groovy.transform.Field
import org.parosproxy.paros.Constant
import org.zaproxy.zap.extension.script.ExtensionScript
import org.zaproxy.zap.extension.websocket.WebSocketMessageDTO

import java.nio.file.Paths

@Field final def wrappedScript = debug()

def debug() {
    def relativeScriptFilePath = "scripts/websocketfuzzerprocessor/WebsocketFuzzerProcessorDefaultTemplate.groovy"

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

void processMessage(def utils, WebSocketMessageDTO message){
    println("Start Debugging...")
    wrappedScript.processMessage(utils, message)
    println("End Debugging...")
}
