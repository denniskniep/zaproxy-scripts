import groovy.transform.Field
import org.parosproxy.paros.Constant
import org.parosproxy.paros.network.HttpMessage
import org.zaproxy.zap.extension.script.ExtensionScript

import java.nio.file.Paths

@Field final def wrappedScript = debug()

def debug() {
    def relativeScriptFilePath = "scripts/proxy/ProxyDefaultTemplate.groovy"

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

boolean proxyRequest(HttpMessage msg) {
    println("Start Debugging...")
    def result = wrappedScript.proxyRequest(msg)
    println("End Debugging with Result:'" + result + "'...")
    return result
}

boolean proxyResponse(HttpMessage msg) {
    println("Start Debugging...")
    def result = wrappedScript.proxyResponse(msg)
    println("End Debugging with Result:'" + result + "'...")
    return result
}



