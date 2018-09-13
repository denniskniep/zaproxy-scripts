import groovy.transform.Field
import net.htmlparser.jericho.Source
import org.parosproxy.paros.Constant
import org.parosproxy.paros.network.HttpMessage
import org.zaproxy.zap.extension.pscan.scanner.ScriptsPassiveScanner
import org.zaproxy.zap.extension.script.ExtensionScript

import java.nio.file.Paths

@Field final def wrappedScript = debug()

def debug() {
    def relativeScriptFilePath = "scripts/passive/PassiveDefaultTemplate.groovy"

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

void scan(ScriptsPassiveScanner ps, HttpMessage msg, Source source) {
    println("Start Debugging...")
    wrappedScript.scan(ps, msg, source)
    println("End Debugging...")
}

boolean appliesToHistoryType(Integer historyType) {
    println("Start Debugging...")
    def result = wrappedScript.appliesToHistoryType(historyType)
    println("End Debugging with Result:'" + result + "'...")
    return result
}



