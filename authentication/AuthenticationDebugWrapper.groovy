import groovy.transform.Field
import org.parosproxy.paros.Constant
import org.parosproxy.paros.network.HttpMessage
import org.zaproxy.zap.authentication.AuthenticationHelper
import org.zaproxy.zap.authentication.GenericAuthenticationCredentials
import org.zaproxy.zap.extension.script.ExtensionScript

import java.nio.file.Paths

@Field final def wrappedScript = debug()

def debug() {
    def relativeScriptFilePath = "scripts/authentication/AuthenticationDefaultTemplate.groovy"

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

HttpMessage authenticate(AuthenticationHelper helper, Map<String, String> paramsValues, GenericAuthenticationCredentials credentials) {
    println("Start Debugging...")
    def result = wrappedScript.authenticate(helper, paramsValues, credentials)
    println("End Debugging...")
    return result
}

String[] getRequiredParamsNames() {
    println("Start Debugging...")
    def result = wrappedScript.getRequiredParamsNames()
    println("End Debugging...")
    return result
}

String[] getOptionalParamsNames() {
    println("Start Debugging...")
    def result = wrappedScript.getOptionalParamsNames()
    println("End Debugging...")
    return result
}

String[] getCredentialsParamsNames(){
    println("Start Debugging...")
    def result = wrappedScript.getCredentialsParamsNames()
    println("End Debugging...")
    return result
}

/*
String getLoggedInIndicator() {
    println("Start Debugging...")
    def result = wrappedScript.getLoggedInIndicator()
    println("End Debugging...")
    return result
}
*/

/*
String getLoggedOutIndicator() {
    println("Start Debugging...")
    def result = wrappedScript.getLoggedOutIndicator()
    println("End Debugging...")
    return result
}
*/

