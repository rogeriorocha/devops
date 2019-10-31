
podTemplate(
    label: 'worker', 
    containers: [
        containerTemplate(args: 'cat', name: 'c-docker', command: '/bin/sh -c', image: 'docker', ttyEnabled: true)
    ],
    volumes: [
      hostPathVolume(mountPath: '/var/run/docker.sock', hostPath: '/var/run/docker.sock')
    ]
){
    def GIT_COMMIT
    def GIT_BRANCH
    node('worker') {
        def GIT_REPOS = git credentialsId: 'rpsr-github', url: 'git@github.com:rogeriorocha/docker-spring-monitor-log.git'
        
        echo GIT_REPOS.toString()
        
        GIT_COMMIT = GIT_REPOS.GIT_COMMIT
        GIT_BRANCH = GIT_REPOS.GIT_BRANCH
        
        echo 'Iniciando Pipeline'
        stage('checkout') {
            
            checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'rpsr-github', url: 'git@github.com:rogeriorocha/docker-spring-monitor-log.git']]])
            sh 'ls -ltra'
            
        }
        stage('package') {
            container('c-docker') {
                withCredentials([usernamePassword(credentialsId: 'docker-hub-rogeriosilvarocha', passwordVariable: 'DH_PASSWORD', usernameVariable: 'DH_USER')]) {
                    
                    sh """
                    docker login -u ${DH_USER} -p ${DH_PASSWORD}
                    docker build -t rogeriosilvarocha/my-fs:0.1 .
                    docker push rogeriosilvarocha/my-fs:0.1
                    docker ps
                    docker image ls
                        
                    """    
                }                

                
            }
        }
        stage('Deploy') {
            
        }    
    }
}