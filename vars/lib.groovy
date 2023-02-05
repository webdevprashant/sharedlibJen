node  {

def build( giturlrepo ,cmdname)  {
    stage "Build-Java-app"
 
    git url: '${giturlrepo}'
    sh "${cmdname}"
    
    sh "sudo docker build -t webdevprashant/javaapp:${BUILD_NUMBER} ."
    sh "sudo docker run  -d -p 1222:8080 --name myjavaappdevenv webdevprashant/javaapp:${BUILD_NUMBER}" 
}
    
    
def test(ip , searchitem)    {
        sh "curl --silent http://${ip}:1223/java-web-app/ |  grep -i ${searchitem}"
}
    
def push()                  {
         withCredentials([string(credentialsId: 'Docker_hub_password', variable: 'VAR_FOR_DOCKERPASS')]) {
                    sh "sudo docker login -u webdevprashant -p $VAR_FOR_DOCKERPASS"
                    }
                    sh "sudo docker push webdevprashant/javaapp-day6:${BUILD_NUMBER}"
    }
    
}
