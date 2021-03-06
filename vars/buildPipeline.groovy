import com.codependent.jenkins.pipelines.Utils

def call(List<String> mavenGoals){
  def processedMavenGoals = mavenGoals.join ' '
  pipeline {
    agent any
    tools { 
      maven 'M3' 
      jdk 'JDK8' 
    }
    stages {
      stage ('Build Application') {
        steps {
          echo 'Building application'
          script {
            def utils = new Utils()
            utils.hello 'Codependent'
          }
          sh '''
             echo "PATH = ${PATH}"
             echo "M2_HOME = ${M2_HOME}"
             ''' 
          sh "mvn $processedMavenGoals -U -Dmaven.test.failure.ignore=true"
        }
      }
      stage ('Finish') {
        steps {
          echo 'Finishing application'
        }
      }
    }
  }
}