import { Component } from '@angular/core';
import { category } from 'src/app/category';
import { FormBuilder , FormGroup } from '@angular/forms';
import { MyserviceService } from 'src/app/myservice.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent {

p:number=1;
count:number=5;


catObj !:FormGroup;
newcategory: category= new category();
allcategory:category[]=[];



  constructor(private formbuild: FormBuilder,private serv:MyserviceService)
  {

    this.getallcategory();

      this.catObj = this.formbuild.group({
          catid:[''],
          catnm:['']
      })

  }

  getallcategory()
    {
      this.serv.getallcategory().subscribe(res=>{
        this.allcategory = res
         },
      err=>console.log(err));
    }

  addCategory()
  {
      this.newcategory.catnm = this.catObj.value.catnm;

      this.serv.addCategory(this.newcategory).subscribe(res=>{
        alert("Category is Added ! ")
        this.getallcategory();}
        , err=>console.log(err))

  }

  changePage(e: number)
  {
    this.p = e;
    this.getallcategory();
  }

editCat(e:any){

this.catObj.controls['catid'].setValue(e.catid)
this.catObj.controls['catnm'].setValue(e.catnm)

}


updateCat(){

this.newcategory.catid = this.catObj.value.catid;
this.newcategory.catnm = this.catObj.value.catnm;

this.serv.updatecategory(this.newcategory).subscribe(res=>{
  alert("Category is updated !")
  this.getallcategory();}
  ,
  err=>console.log(err)
  )

}


deleteCat(e:any){

this.serv.deletecategory(e).subscribe(res=>{alert("Category is deleted ! ");
this.getallcategory();
},
  err=>console.log(err))


}


}



