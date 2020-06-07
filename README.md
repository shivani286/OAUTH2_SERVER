# OAUTH2_SERVER

1) Registration First Api call:

Api: http://localhost:8181/registration

Request :

{
 "emailId":"anisha.lodhi@gmail.com",
 "firstName":"anisha",
 "lastName":"lodhi",
 "primaryContactNumber":"9028223620",
 "companyName" :"ORG_TEST_LOGIN_ADMIN_2"
}

2) Confirm Password:

Api : http://localhost:8181/registration/confirm

Request:

{
	 "password": "a@asd",
	 "confirmPassword":"a@asd",
	 "confirmationCode":"ajluNjl0dW5vNXFycHJxdHZ2bjY=",
	 "emailId":"anisha.lodhi@gmail.com"
}

3) AuthToken api:

Api : http://localhost:8181/oauth/token

Authentication --->  Type =  Basic Auth
		     userName = ClientId
		     password = ClientSecret

Form Data ----------------------> grant_type = password
Registor email enter in --------> username  = anisha.lodhi@gmail.com
		                  password = a@asd

Response : 

{
    "access_token": "2b94a2ba-43a9-4563-85cd-533e4ce48a49",
    "token_type": "bearer",
    "refresh_token": "e9749fab-d060-4258-be56-ddfcedea9037",
    "expires_in": 3599,
    "scope": "READ WRITE"
}

4) User Detail Get Call Api:

Api: http://localhost:8181/user/detail/by/1

Header add ------> Key = Authorization
		   Value = bearer 2b94a2ba-43a9-4563-85cd-533e4ce48a49

Response:

{
    "userId": 1,
    "emailId": "thakur.shivani220@gmail.com",
    "organization": {
        "organizationId": 1,
        "name": "ORG_TEST_LOGIN",
        "emailId": "thakur.shivani220@gmail.com",
        "primaryContactNumber": "9028223619"
    },
    "firstName": "Shivani",
    "lastName": "Thakur",
    "companyName": null,
    "contactNumber": "9028223619",
    "createdOn": "2020-06-06T16:37:21.000+0000",
    "updatedOn": "2020-06-06T16:37:21.000+0000",
    "enabled": true,
    "accountNonExpired": true,
    "credentialsNonExpired": true,
    "accountNonLocked": true,
    "roles": [
        {
            "roleId": 1,
            "name": "ROLE_admin",
            "permissions": [
                {
                    "permissionId": 1,
                    "name": "create_profile"
                }
            ]
        }
    ]
}

