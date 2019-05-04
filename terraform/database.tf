module "rds" {
  source  = "terraform-aws-modules/rds/aws"
  version = "1.21.0"

  identifier = "gescolar-rds"

  engine = "mysql"
  engine_version = "5.7.19"
  instance_class = "db.t2.micro"
  allocated_storage = "20"

  name = "gescolar"
  username = "root"
  password = "YourPwdShouldBeLongAndSecure!"
  port = "3306"
	
  family = "mysql5.7"
  major_engine_version = "5.7"
  		
	
  vpc_security_group_ids = ["${aws_security_group.database.id}"]

  maintenance_window = "Thu:03:30-Thu:05:30"
  backup_window = "05:30-06:30"
  storage_type = "gp2"
  

  subnet_ids = "${flatten(chunklist(aws_subnet.private_subnet.*.id, 1))}"

}