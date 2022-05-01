export class User {
  public 'emailId'!: any;
  public 'firstName'!: any;
  public 'lastName'!: any;
  public 'mobile'!: any;
  public 'password'!: any;
  public 'roles'!: any[];
  public 'username'!: any;

  getFirstName(): string {
    return this.firstName;
  }
}
