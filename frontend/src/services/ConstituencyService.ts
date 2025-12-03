import type { ConstituencyPartyVotes } from "@/interfaces/IElectionData";


/**
 *
 * This service does a fetches
 * the constituency data results
 */
const API_BASE_URL = 'http://localhost:8080/api';
export class ConstituencyService{

/**
* @param electionId hold the id of the elections, the value of the Id attribute from the ElectionIdentifier tag.
* @param constituencyName holds the name of the selected constituency
*/
  public static async getConstituencyData(electionId: string, constituencyName: string): Promise<ConstituencyPartyVotes[]> {
    const url: string = `${API_BASE_URL}/elections/${electionId}/${constituencyName}`
    // Response holds the fetch to the endpoint
    try {
      // Response holds the fetch to the endpoint
      const response: Response = await fetch(url, {
        method: "GET",
        headers: {"Accept": "application/json"}
      })
      if (!response.ok) {
        throw new Error(`GET ${url} failed → ${response.status} ${response.statusText}`);
      }

      return await response.json();
    } catch (error) {
      console.error(`error fetching constituencyPartyResults ${url}`,  error instanceof Error ? error.message: error);
      throw error;
    }

  }

  /**
   *
   * @param electionId hold the id of the elections, the value of the Id attribute from the ElectionIdentifier tag.
   * @param constituencyName holds the name of the selected constituency
   */
  public static async getConstituencyVotesPercentage(electionId: string, constituencyName: string): Promise<number> {
    const url: string = `${API_BASE_URL}/elections/${electionId}/${constituencyName}/votes-percentage`
    // Response holds the fetch to the endpoint
    try {
      // Response holds the fetch to the endpoint
      const response: Response = await fetch(url, {
        method: "GET",
        headers: {"Accept": "application/json"}
      })
      if (!response.ok) {
        throw new Error(`GET ${url} failed → ${response.status} ${response.statusText}`);
      }

      return await response.json();
    } catch (error) {
      console.error(`error fetching constituencyVotesPercentage ${url}`,  error instanceof Error ? error.message: error);
      throw error;
    }
  }

  /**
   *
   * @param electionId hold the id of the elections, the value of the Id attribute from the ElectionIdentifier tag.
   * @param constituencyName holds the name of the selected constituency
   */
  public static async getTotalConstituencyVote(electionId: string, constituencyName: string): Promise<number> {
    const url: string = `${API_BASE_URL}/elections/${electionId}/${constituencyName}/totalVotes`
    // Response holds the fetch to the endpoint
    try {
      // Response holds the fetch to the endpoint
      const response: Response = await fetch(url, {
        method: "GET",
        headers: {"Accept": "application/json"}
      })
      if (!response.ok) {
        throw new Error(`GET ${url} failed → ${response.status} ${response.statusText}`);
      }

      return await response.json();
    } catch (error) {
      console.error(`error fetching totalConstituencyVotes ${url}`, error instanceof Error ? error.message: error);
      throw error;
    }
  }

}
