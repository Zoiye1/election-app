/**
 *
 * This service does a fetch to the endpoint /{electionId} to get
 * the constituency data results
 */
 export class ElectionService {
  /**
   * @param electionId hold the id of the elections, the value of the Id attribute from the ElectionIdentifier tag.
   * @param folder holds the folder name of where the xml file is located
   */
  public async getElectionData(electionId: string): Promise<any> {
    const url: string = `http://localhost:8080/elections/${electionId}`
    // Response holds the fetch to the endpoint
    try {
      // Response holds the fetch to the endpoint
      const response: Response = await fetch(url, {
        method: "GET",
        headers:{"Accept": "application/json"}
      })
      if(!response.ok){
        throw new Error("request failed" + response.statusText);
      }
      return await response.json();
    }
    catch(error) {
      console.error('Fetch error:', error);
      throw error;
    }

  }

  public async getTotalVotes(): Promise<number> {
    const url: string = `http://localhost:8080/elections/TK2023/TotalVotes`

    try {
      const response: Response = await fetch(url, {
        method: "GET",
        headers:{"Accept": "application/json"}
      })
      if(!response.ok){
        throw new Error("request failed" + response.statusText);
      }
      return await response.json();
    }
    catch(error) {
      console.error('Fetch error:', error);
      throw error;
    }
  }
}
