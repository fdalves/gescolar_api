resource "aws_key_pair" "keypair" {
  public_key = "${file("key/gescolar_key.pub")}"
}


resource "aws_instance" "instances" {
  count = 3

  ami = "ami-0de53d8956e8dcf80"
  instance_type = "t2.micro"

  subnet_id = "${element(aws_subnet.public_subnet.*.id, count.index)}"
  
  key_name = "${aws_key_pair.keypair.key_name}"
  
  vpc_security_group_ids = ["${aws_security_group.allow_ssh.id}"]

  tags {
    Name = "gescolar_instances"
  }
}
