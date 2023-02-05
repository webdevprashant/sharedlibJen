node  {

def build( giturlrepo ,cmdname)  {
    stage "Build-React-app"
 
    git url: '${giturlrepo}'
    sh "${cmdname}"
    
    sh "sudo docker build -t webdevprashant/reactapp:${BUILD_NUMBER} ."
    sh "sudo docker run  -d -p 1222:8080 --name myreactapp webdevprashant/reactapp:${BUILD_NUMBER}" 
}
    
    
def test(ip , searchitem)    {
        sh "curl --silent http://${ip}:3000 |  grep -i ${searchitem}"
}
    
def push()                  {
         withCredentials([string(credentialsId: 'Docker_hub_password', variable: 'VAR_FOR_DOCKERPASS')]) {
                    sh "sudo docker login -u webdevprashant -p $VAR_FOR_DOCKERPASS"
                    }
                    sh "sudo docker push webdevprashant/reactapp:${BUILD_NUMBER}"
    }
    
}