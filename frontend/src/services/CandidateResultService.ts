import type { CandidateResult } from '@/interfaces/CandidateResult';

const API_BASE_URL = import.meta.env.VITE_API_BASE_URL;

/**
 * Fetches the top candidates by votes for a specific election
 * @param {string} electionId - The election identifier (e.g., "TK2023")
 * @returns {Promise<CandidateResult[]>} List of top candidates with votes and party
 */
export async function getTopCandidatesByElection(electionId: string): Promise<CandidateResult[]> {
  try {
    const response = await fetch(`${API_BASE_URL}/candidates/top?electionId=${electionId}`);

    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }

    return await response.json();
  } catch (error) {
    console.error('Error fetching top candidates:', error);
    throw error;
  }
}
