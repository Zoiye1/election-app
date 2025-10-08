export class LoginService {
  public async verifyUser(email: string, password: string): Promise<boolean> {
    const url: string = `http://localhost:8080/user/verify`
    const requestBody = {
      email: email,
      password: password
    };

    try {
      const response: Response = await fetch(url, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Accept": "application/json"
        },
        body: JSON.stringify(requestBody)
      })

      if (!response.ok) {
        throw new Error("Request failed: " + response.statusText);
      }

      return await response.json();
    }
    catch (error) {
      console.error('Fetch error:', error);
      throw error;
    }
  }
}
