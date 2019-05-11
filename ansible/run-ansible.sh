echo "Starting ansible..."

ANSIBLE_HOST_KEY_CHECKING=false ansible-playbook -i ../terraform/hosts --private-key ../terraform/key/gescolar_key gescolar-playbook.yml -v