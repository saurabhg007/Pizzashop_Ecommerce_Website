import { Component } from '@angular/core';
import { MyserviceService } from 'src/app/myservice.service';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth.service';
@Component({
  selector: 'app-adminpanel',
  templateUrl: './adminpanel.component.html',
  styleUrls: ['./adminpanel.component.css']
})
export class AdminpanelComponent {

  toggle?:boolean=false;
  u?:string;
  isSignedin: boolean;
  signedinUser: string;
  router: any;
  greetingService: any;
  greeting: any;

  constructor(private serv:MyserviceService,private route:Router,private authService: AuthService,){
  }
  ngOnInit()
  {
    this.u=MyserviceService.username
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


  categorybtn()
  {
      this.toggle=true;
  }

  productbtn()
  {
    this.toggle=false;

  }

  logoutbtn()
  {
    MyserviceService.login=false;
    this.route.navigate(["/"]);
  }

}
