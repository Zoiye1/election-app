import type { ConstituencyPartyVotes } from "@/interfaces/IElectionData";
import type { Election } from "@/interfaces/IElectionData";

/**
 *
 * This service does a fetch to the endpoint /{electionId} to get
 * the constituency data results
 */
 export class ElectionService {
  /**
   * @param electionId hold the id of the elections, the value of the Id attribute from the ElectionIdentifier tag.
   * @param constituencyName holds the name of the selected constituency
   * @param folder holds the folder name of where the xml file is located
   */

  public async getConstituencyData(electionId: string, constituencyName: string): Promise<ConstituencyPartyVotes[]> {
    const url: string = `http://localhost:8080/api/elections/${electionId}/${constituencyName}`
    // Response holds the fetch to the endpoint
    try {
      // Response holds the fetch to the endpoint
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

 public async getMunicipalityData(electionId: string, municipalityName: string): Promise<ConstituencyPartyVotes[]> {
    const url: string = `http://localhost:8080/api/elections/${electionId}/municipalities/${municipalityName}`
    // Response holds the fetch to the endpoint
    try {
      // Response holds the fetch to the endpoint
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

  public static async getTotalVotes(): Promise<number> {
    const url: string = `http://localhost:8080/api/elections/TK2023/total-votes`

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
    const url: string = `http://localhost:8080/api/elections/${electionId}`
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

  public static async getConstituencyVotesPercentage(electionId: string, constituencyName: string): Promise<number> {
    const url: string = `http://localhost:8080/api/elections/${electionId}/${constituencyName}/votes-percentage`
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
