import type { TopNationalParty } from "@/interfaces/IElectionData.ts";

/**
 *
 * Service for fetching national party results
 */
const API_BASE_URL = 'http://localhost:8080/api';

export class NationalPartyService {


  /**
   * Fetches the top parties by votes for a specific election
   * @param electionId the election identifier (e.g., "TK2023")
   * @returns Promise with array of top national parties
   */
  public static async getTopParties(electionId: string): Promise<TopNationalParty[]> {
    const url: string = `${API_BASE_URL}/parties/top?electionId=${electionId}`;

    try {
      const response: Response = await fetch(url, {
        method: "GET",
        headers: { "Accept": "application/json" }
      });

      if (!response.ok) {
        throw new Error("request failed " + response.statusText);
      }

      return await response.json();
    } catch (error) {
      console.error('Fetch error:', error);
      throw error;
    }
  }
}
