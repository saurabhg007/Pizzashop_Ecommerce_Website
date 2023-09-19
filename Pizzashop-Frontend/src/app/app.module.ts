import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { FormsModule ,ReactiveFormsModule } from '@angular/forms';
import { NgxPaginationModule } from 'ngx-pagination';
import { ListproductComponent } from './productdetails/listproduct/listproduct.component';
import { CustomerpanelComponent } from './customerdashboard/customerpanel/customerpanel.component';
import { RouterModule } from '@angular/router';
import { PagenotfoundComponent } from './pagenotfoundcomp/pagenotfound/pagenotfound.component';
import { AppComponent } from './app.component';
import { CategoryComponent } from './categorydetails/category/category.component';
import { AdminpanelComponent } from './admindashboard/adminpanel/adminpanel.component';
import { AboutComponent } from './about/about.component';
import { ChefComponent } from './chef/chef.component';
import { GallaryComponent } from './gallary/gallary.component';
import { MenuComponent } from './menu/menu.component';
import { TestimonialComponent } from './testimonial/testimonial.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { SigninComponent } from './signin/signin.component';
import { SignupComponent } from './signup/signup.component';
import { HttpInterceptorService } from './http-interceptor.service';





@NgModule({
  declarations: [
    AppComponent,
    CategoryComponent,
    ListproductComponent,
    LoginComponent,
    CustomerpanelComponent,
    AdminpanelComponent,
    PagenotfoundComponent,
    MenuComponent,
    SigninComponent,
    SignupComponent,
    HomeComponent,
    AboutComponent,
    MenuComponent,
    GallaryComponent,
    ChefComponent,
    TestimonialComponent

  ],
  imports: [
    BrowserModule,
    NgxPaginationModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,RouterModule.forRoot([
        {path:"admin",component:AdminpanelComponent},
      //  {path:"",redirectTo:"/home",pathMatch:"full"},
         {path:"customer",component:CustomerpanelComponent},
         { path: 'home', component: HomeComponent },
         { path: 'about', component: AboutComponent },
         { path: 'menu', component: MenuComponent },
         { path: 'signin', component: SigninComponent },
         { path: 'signup', component: SignupComponent },
         { path: 'gallary', component: GallaryComponent },
         { path: '', component: SigninComponent },
       //  { path: 'home', component: HomeComponent },
        // {path:"**",component:PagenotfoundComponent}
  ])
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpInterceptorService,
      multi: true
   }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
