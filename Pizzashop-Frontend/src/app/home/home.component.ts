import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { GreetingService } from '../greeting.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
isSignedin = false;

	signedinUser: string = '';

	greeting: any[] = [];

	constructor(private route: ActivatedRoute, private router: Router, private http: HttpClient, private authService: AuthService, private greetingService: GreetingService) {}

	ngOnInit() {
		this.isSignedin = this.authService.isUserSignedin();
		this.signedinUser = this.authService.getSignedinUser();

		if(!this.authService.isUserSignedin()) {
			this.router.navigateByUrl('signin');
		}

		if(this.isSignedin) {
			this.greetingService.getByUserRole().subscribe((result: string) => this.greeting.push(result), () => console.log('/user - You are not authorize'));
			this.greetingService.getByAdminRole().subscribe((result: string) => this.greeting.push(result), () => console.log('/admin - You are not authorized'));
			this.greetingService.getByUserOrAdminRole().subscribe((result: string) => this.greeting.push(result), () => console.log('/userOrAdmin - You are not authorized'));
		//	this.greetingService.getByAnonymousRole().subscribe((result: string) => this.greeting.push(result), () => console.log('/anonymous - You are not authorized'));
		}
	}

	doSignout() {
		this.authService.signout();
	}


}
