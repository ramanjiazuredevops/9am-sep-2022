resource "aws_instance" "demo" {
					ami 				      =     "ami-0509f816fdd94dec7"
					instance_type		      =     "t2.micro"
					monitoring			      =     "false"
					key_name			      =     "gel"
					subnet_id			      =     "subnet-0170235fe2e11f4a0"
					associate_public_ip_address   =     "true"
					source_dest_check		      =     "true"
					root_block_device {
					volume_type			      =     "gp2"
					volume_size			      =     "100"
					delete_on_termination	      =     "false"
					}
					ebs_block_device {
                    encrypted   = "true"
                    device_name = "/dev/xvdd"
                    volume_size = "40"
                    volume_type = "gp2"
                    }

					}
