import groovy.transform.Field
import org.parosproxy.paros.Constant
import org.parosproxy.paros.core.scanner.VariantCustom
import org.parosproxy.paros.network.HttpMessage
import org.zaproxy.zap.extension.script.ExtensionScript

import java.nio.file.Paths

@Field final def wrappedScript = debug()

def debug() {
    def relativeScriptFilePath = "scripts/variant/InputVectorDefaultTemplate.groovy"

    def scriptFilePath = Paths
            .get(Constant.getZapHome(), ExtensionScript.SCRIPTS_DIR, relativeScriptFilePath)
            .toAbsolutePath()
            .toString()

    def script = new File(scriptFilePath)
    def scriptFunctions = evaluate(script)
    return scriptFunctions
}

void parseParameters(VariantCustom helper, HttpMessage msg){
    println("Start Debugging...")
    wrappedScript.parseParameters(helper, msg)
    println("End Debugging...")
}

void setParameter(VariantCustom helper, HttpMessage msg, String param, String value, boolean escaped) {
    println("Start Debugging...")
    wrappedScript.setParameter(helper, msg, param, value, escaped)
    println("End Debugging...")
}



