def build( giturlrepo ,cmdname)
node {
    stage "Build-Java-app"
 
    git url: '${giturlrepo}'
    sh "${cmdname}"

}
