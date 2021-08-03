import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddEditComponent } from './add-edit/add-edit.component';
import { ListComponent } from './list/list.component';
import { ProfileComponent } from './profile/profile.component';
import { UserComponent } from './user.component';

const routes: Routes = [
  {
      path: '', component: UserComponent,
      children: [
          { path: '', component: ListComponent },
          { path: 'add', component: AddEditComponent },
          { path: 'profile', component: ProfileComponent },
          { path: 'edit/:id', component: AddEditComponent }
      ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
