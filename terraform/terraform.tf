terraform {
  backend "s3" {
    bucket = "gescolar-terraform"
    key = "gescolar-terraform-key"
    region = "us-east-1"
    profile = "terraform"
  }
}