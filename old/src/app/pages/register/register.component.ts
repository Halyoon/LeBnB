import { Component } from '@angular/core';
import {AuthenticationRequest} from "../../model/authentication-request";
import {AuthenticationResponse} from "../../model/authentication-response";

import {Router} from "@angular/router";
import { AuthenticationService } from 'src/app/services/authentication.service';
import { RegisterRequest } from '../../model/register-request ';
import { VerificationRequest } from 'src/app/model/verification-request';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {

  registerRequest: RegisterRequest = {};
  authResponse: AuthenticationResponse = {};
  message = '';
  otpCode = '';

  constructor(
    private authService: AuthenticationService,
    private router: Router
  ) {
  }

  registerUser() {
    this.message = '';
    this.authService.register(this.registerRequest)
    // .subscribe(({ response }) => {
      
    //   if(response.body){
    //   this.authResponse=response.body;
    //   console.log('Token:', response.body.token);
    //   console.log('MFA enabled:', response.body.mfaEnabled);
    //   console.log('Secret image URI:', response.body.secretImageUri);
    //   }
    //   else {
    //     console.log("noBody");
        
    //           // inform the user
    //           this.message = 'Account created successfully\nYou will be redirected to the Login page in 3 seconds';
    //           setTimeout(() => {
    //             this.router.navigate(['login']);
    //           }, 3000)
    //         }
          

    // });
      .subscribe({
        next: (response) => {
          console.log(response);
          
          if (response) {
            this.authResponse = response.body;
            console.log(response.body.secretImageUri);
            
          } else {
            // inform the user
            this.message = 'Account created successfully\nYou will be redirected to the Login page in 3 seconds';
            setTimeout(() => {
              this.router.navigate(['login']);
            }, 3000)
          }
        }
      });


      
  }

  verifyTfa() {
    this.message = '';
    const verifyRequest: VerificationRequest = {
      email: this.registerRequest.email,
      code: this.otpCode
    };
    this.authService.verifyCode(verifyRequest)
    .subscribe({
      next: (response) => {
        console.log(response);
          this.message = 'Account created successfully\nYou will be redirected to the Welcome page in 3 seconds';
          // setTimeout(()  => {
          //   localStorage.setItem('token', response.token);
          //   this.router.navigate(['welcome']);
          // }, 3000);
        }
       } )};
  

 
}