node  {
def build( giturlrepo ,cmdname)  {

    stage "Build-Java-app"
 
    git url: '${giturlrepo}'
    sh "${cmdname}"

}
}
