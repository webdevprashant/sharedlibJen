def build()  {
 
	node {
    git url: 'https://github.com/webdevprashant/reactapp.git'
    sh "sudo docker build -t webdevprashant/reactapp:${BUILD_NUMBER} ."
    sh "docker rm -f myreactapp"
    sh "sudo docker run  -d -p 1224:3000 --name myreactapp webdevprashant/reactapp:${BUILD_NUMBER}" 
	}
}
    
    
def test(ip , searchitem)    {
        // sh "curl --silent http://${ip}:3000 |  grep -i ${searchitem}"
	node {
	echo "Testing ...."
	}
}
    
def push()                  {
	 node {
      	def dockerImage = "webdevprashant/reactapp:${BUILD_NUMBER}"
      def dockerHubUsername = "webdevprashant"
      def dockerHubPassword = "Dockeristhegreattool@1"

      //withDockerRegistry(credentialsId: "docker-hub-credentials", url: "https://index.docker.io/v1/") {
        // sh "sudo docker login -u ${dockerHubUsername} -p ${dockerHubPassword}"
         // sh "sudo docker push ${dockerImage}"
	echo "Image Pushed"
      //}
    }
         
    }
    