import groovy.transform.Field
import org.parosproxy.paros.Constant
import org.zaproxy.zap.extension.script.ExtensionScript
import org.zaproxy.zap.extension.scripts.ExtenderScriptHelper

import java.nio.file.Paths

@Field final def wrappedScript = debug()

def debug() {
    def relativeScriptFilePath = "scripts/extender/ExtenderDefaultTemplate.groovy"

    def scriptFilePath = Paths
            .get(Constant.getZapHome(), ExtensionScript.SCRIPTS_DIR, relativeScriptFilePath)
            .toAbsolutePath()
            .toString()

    def script = new File(scriptFilePath)
    def scriptFunctions = evaluate(script)
    return scriptFunctions
}

void install(ExtenderScriptHelper helper) {
    println("Start Debugging...")
    wrappedScript.install(helper)
    println("End Debugging...")
}

void uninstall(ExtenderScriptHelper helper) {
    println("Start Debugging...")
    wrappedScript.uninstall(helper)
    println("End Debugging...")
}
