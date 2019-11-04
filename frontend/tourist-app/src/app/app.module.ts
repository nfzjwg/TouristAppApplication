import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { HeaderComponent } from './pages/header/header.component';
import { TopPanelComponent } from './pages/top-panel/top-panel.component';
import { RegisterComponent } from './pages/register/register.component';
import { RegisterPanelComponent } from './pages/register-panel/register-panel.component';
import { HttpClientModule } from "@angular/common/http";
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from "@angular/common";
import { LoginComponent } from './pages/login/login.component';
import { CarComponent } from './pages/car/car.component';
import { UploadcarComponent } from './pages/uploadcar/uploadcar.component';
import { MotorcycleComponent } from './pages/motobikes/motorcycle.component';
import { UploadmotobikeComponent } from './pages/uploadmotobike/uploadmotobike.component';
import { CarRentComponent } from './pages/car-rent/car-rent.component';

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    HeaderComponent,
    TopPanelComponent,
    RegisterComponent,
    RegisterPanelComponent,
    LoginComponent,
    CarComponent,
    UploadcarComponent,
    MotorcycleComponent,
    UploadmotobikeComponent,
    CarRentComponent
  ],
  imports: [
    
    BrowserModule,
    AppRoutingModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    CommonModule
     
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
