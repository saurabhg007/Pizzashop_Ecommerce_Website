import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { Request } from '../Request';

@Component({
  selector: 'app-userlogin',
  templateUrl: './userlogin.component.html',
  styleUrls: ['./userlogin.component.css']
})
export class UserloginComponent {
	username: string = '';
	password : string = '';

	isSignedin = false;

	error: string = '';

	constructor(private route: ActivatedRoute, private router: Router, private authService: AuthService) {}

	ngOnInit() {
		this.isSignedin = this.authService.isUserSignedin();

		if(this.isSignedin) {
			this.router.navigateByUrl('/customer');
		}
	}

	doSignin() {
		if(this.username !== '' && this.username !== null && this.password !== '' && this.password !== null) {
			const request: Request = { userName: this.username, userPwd: this.password};

			this.authService.signin(request).subscribe((result)=> {
				this.router.navigateByUrl('/customer');

			}, () => {
				this.error = 'Either invalid credentials or something went wrong';
			});
		} else {
			this.error = 'Invalid Credentials';
		}
	}

}