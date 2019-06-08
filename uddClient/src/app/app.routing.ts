import {ModuleWithProviders} from '@angular/core';
import { RouterModule, Routes } from "@angular/router";
import {HomepageComponent} from './components/homepage/homepage.component'
import {RegisterComponent} from './components/register/register.component';
import {LoginComponent} from './components/login/login.component';
import { UploadComponent } from './components/upload/upload.component';
import { SearchComponent } from './components/search/search.component';
import { ResultComponent } from './components/result/result.component';
import { PaperComponent } from './components/paper/paper.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';

const appRoutes: Routes =
    [
        { path: '', component: HomepageComponent},
        { path: 'register', component: RegisterComponent },
        { path: 'login', component: LoginComponent },
        { path: 'upload', component: UploadComponent},
        { path: 'search', component: SearchComponent},
        { path: 'result/:result', component: ResultComponent},
        { path: 'paper', component: PaperComponent},
        { path: 'user-profile', component: UserProfileComponent}
    ];
export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
