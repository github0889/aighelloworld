node {
	def mvnHome = tool name: 'maven-3.5.4', type: 'maven'
    def mvnCMD = "${mvnHome}\\bin"
   	stage('SCM Checkout'){
      //GitHub
   		git url: 'https://github.com/github0889/aighelloworld.git'
   	}
    stage ('Code Analysis'){
      //Sonarqube scanner with unit tests
      bat "${mvnCMD}\\mvn sonar:sonar"
    }
    stage('Build Automation'){
       bat "${mvnCMD}\\mvn clean package"
    }
    stage('Build Management'){
        //Artifactory
        def server = Artifactory.server('local_artifactory')
        def buildInfo = Artifactory.newBuildInfo()
        buildInfo.name = 'Beach'
        buildInfo.number = '1.0.0.'+ BUILD_NUMBER
        server.publishBuildInfo buildInfo
        def uploadSpec = """{
        "files": [
     {
        "pattern": "**\\*.war",
        "target": "java-dev-local/Web-Projects/Beach-WebApp/${buildInfo.number}/"
            }
        ]
    }"""
        server.upload(uploadSpec)
}
    stage ('Deploy'){
      //Tomcat
        bat 'xcopy "target\\Beach.war" "C:\\Program Files (x86)\\devops\\apache-tomcat-8.5.32\\webapps\\" /Y /E /I'
    }
    stage('Email Notification'){
    // send to email
    emailext (
       subject: "STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
       to: "mollala89@gmail.com,nishanth.mollala@aig.com",
       body: """<p>STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
       <p>Check console output at "<a href="${env.BUILD_URL}">${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>"</p>""",
       recipientProviders: [[$class: 'DevelopersRecipientProvider']]
              )
    }
}
