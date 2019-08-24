import {IUser} from '../Interfaces/IUser';

export class PostUser implements IUser {
    username: string;
    firstName: string;
    lastName: string;
    password: string;
    birthdate: number;
    email: string;

    constructor(username: string, firstName: string, lastName: string, password: string, birthdate: number, email: string) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.birthdate = birthdate;
        this.email = email;
    }
}
