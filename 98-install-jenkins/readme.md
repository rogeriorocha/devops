

# crate pv
kubectl apply -f 01-storage.yaml


Vamos aplicar nossos novos recursos:
helm install --name jenkins --set persistence.ExistingClaim=jenkins --set master.serviceType=NodePort --set master.nodePort=30808 --namespace devops stable/jenkins

Para maiores detalhes consulte a documentação do chart.

O Jenkins irá precisar das permissões para listar, criar e excluir seus pods escaláveis no namespace devops: kubectl create rolebinding sa-devops-role-clusteradmin --clusterrole=cluster-admin --serviceaccount=devops:default --namespace=devops

E, futuramente através do helm, teremos que fazer algumas ações junto ao kube-system:
kubectl create rolebinding sa-devops-role-clusteradmin-kubesystem --clusterrole=cluster-admin --serviceaccount=devops:default --namespace=kube-system

Um Jenkins, up and running em poquíssimos segundos, com auto-scale configurado dentro do próprio Kubernetes, pode isso produção?!


===============
NOTES:
1. Get your 'admin' user password by running:
  printf $(kubectl get secret --namespace devops jenkins -o jsonpath="{.data.jenkins-admin-password}" | base64 --decode);echo
2. Get the Jenkins URL to visit by running these commands in the same shell:
  export NODE_PORT=$(kubectl get --namespace devops -o jsonpath="{.spec.ports[0].nodePort}" services jenkins)
  export NODE_IP=$(kubectl get nodes --namespace devops -o jsonpath="{.items[0].status.addresses[0].address}")
  echo http://$NODE_IP:$NODE_PORT/login

3. Login with the password from step 1 and the username: admin



journalctl -fu docker.service