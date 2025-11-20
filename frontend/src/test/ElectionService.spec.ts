import { describe, it, expect, beforeEach, vi } from 'vitest';
import type { Mock } from 'vitest';
import { ElectionService } from '@/services/ElectionService';
import type { ConstituencyPartyVotes, Constituency } from '@/interfaces/IElectionData';
/**
 * Tests the Election service and all of
 * it's functions
 */

describe('ElectionService', () => {
  let service: ElectionService;

  beforeEach(() => {
    service = new ElectionService();
    global.fetch = vi.fn() as Mock;
  });

  it("receive election data successfully", async () => {

    // ARRANGE
    const constituency: Constituency = {
      id: 1,
      name: "Amsterdam"
    };

    const mockData: ConstituencyPartyVotes[] = [
      { id: 1, partyName: "VVD", votes: 12345, constituency },
      { id: 2, partyName: 'PvdA', votes: 6789, constituency }
    ];

    (fetch as Mock).mockResolvedValueOnce({
      ok: true,
      json: async () => mockData
    });

    // ACT

    const result = await service.getConstituencyData('TK2023', 'Amsterdam');

    // ASSERT

    expect(result).toEqual(mockData);
    expect(fetch).toHaveBeenCalledWith(
      'http://localhost:8080/elections/TK2023/Amsterdam',
      { method: 'GET', headers: { Accept: 'application/json' } }
    );
  });

  it('throws error on wrong response', async () => {
    // ARRANGE
    (fetch as Mock).mockResolvedValueOnce({
      ok: false,
      statusText: 'Not Found'
    });

    // ACT + ASSERT

    await expect(service.getConstituencyData('TK2023', 'Amsterdam'))
      .rejects
      .toThrow('request failed Not Found');
  });

  it('throws error on fetch exception', async () => {

    // ARRANGE
    (fetch as Mock).mockRejectedValueOnce(new Error('Network error'));

    // ACT + ASSERT

    await expect(service.getConstituencyData('TK2023', 'Amsterdam'))
      .rejects
      .toThrow('Network error');
  });
});
