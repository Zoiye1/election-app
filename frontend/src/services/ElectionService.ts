import type { ConstituencyPartyVotes } from "@/interfaces/IElectionData";
import type { Election } from "@/interfaces/IElectionData";

/**
 *
 * This service does a fetch to the endpoint /{electionId} to get
 * the constituency data results
 */
const API_BASE_URL = 'http://localhost:8080/api';
 export class ElectionService {


  public static async getTotalVotes(): Promise<number> {
    const url: string = `${API_BASE_URL}/elections/TK2023/total-votes`

    try {
      const response: Response = await fetch(url, {
        method: "GET",
        headers: {"Accept": "application/json"}
      })
      if (!response.ok) {
        throw new Error("request failed" + " " + response.statusText);
      }
      return await response.json();
    } catch (error) {
      console.error('Fetch error:', error);
      throw error;
    }
  }


  public async getElection(electionId: string): Promise<Election> {
    const url: string = `${API_BASE_URL}elections/${electionId}`
    // Response holds the fetch to the endpoint
    try {
      // Response holds the fetch to the endpoint
      const response: Response = await fetch(url, {
        method: "GET",
        headers: {"Accept": "application/json"}
      })
      if (!response.ok) {
        throw new Error("request failed ${response.statusText}");
      }
      return await response.json();
    } catch (error) {
      console.error('Fetch error:', error);
      throw error;
    }
  }

}
