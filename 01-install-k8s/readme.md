=== DOCKER
sudo apt-get update
sudo apt-get install -y apt-transport-https ca-certificates curl software-properties-common
sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
sudo apt-get update
sudo apt-get install docker-ce=18.06.2~ce~3-0~ubuntu

sudo usermod -aG docker $USER

=== K8S
sudo su
curl -s https://packages.cloud.google.com/apt/doc/apt-key.gpg | apt-key add -
cat <<EOF >/etc/apt/sources.list.d/kubernetes.list
deb https://apt.kubernetes.io/ kubernetes-xenial main
EOF
apt-get update
apt-get install -y kubelet=1.16.0-00 kubeadm=1.16.0-00 kubectl=1.16.0-00
apt-mark hold kubelet kubeadm kubectl
exit


=== disable swap
sudo sed -i '/ swap / s/^\(.*\)$/#\1/g' /etc/fstab

=== start 
sudo kubeadm init --pod-network-cidr=10.10.0.0/24

===
kubectl cluster-info

=== Pod Network Addon
kubectl apply -f https://raw.githubusercontent.com/coreos/flannel/master/Documentation/kube-flannel.yml

===
kubectl get nodes



=== Dashboard
kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v1.10.1/src/deploy/recommended/kubernetes-dashboard.yaml

kubectl delete service kubernetes-dashboard-nodeport --namespace=kube-system

# Export dashboard
kubectl expose deployment kubernetes-dashboard --name=kubernetes-dashboard-nodeport --port=443 --target-port=8443 --type=NodePort -n kube-system

http://40.67.168.158:3229

# Criando Service Account e associando permissao 'cluster-admin'
kubectl create serviceaccount kubeadmin -n kube-system 
kubectl create clusterrolebinding kubeadmin-binding --clusterrole=cluster-admin --serviceaccount=kube-system:kubeadmin

kubectl describe sa kubeadmin -n kube-system
kubectl get secret <TOKEN-ID> -n kube-system -o yaml
echo `echo <TOKEN> | base64 --decode`

