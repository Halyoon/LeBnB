import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RegisterRequest } from '../model/register-request ';
import { AuthenticationResponse } from '../model/authentication-response';
import { VerificationRequest } from '../model/verification-request';
import { AuthenticationRequest } from '../model/authentication-request';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private baseUrl: string ="http://localhost:8080/api/auth"

  constructor(
    private http :HttpClient
  ) { }

  register(
    registerRequest : RegisterRequest
  ){
    return this.http.post<any>
    (`${this.baseUrl}/register`,registerRequest)

  }

  verifyCode(verificationRequest: VerificationRequest) {
    return this.http.post<any>
    (`${this.baseUrl}/verify`, verificationRequest);
  }

  login(
    authRequest: AuthenticationRequest
  ) {
    return this.http.post<any>
    (`${this.baseUrl}/authenticate`, authRequest);
  }
}
