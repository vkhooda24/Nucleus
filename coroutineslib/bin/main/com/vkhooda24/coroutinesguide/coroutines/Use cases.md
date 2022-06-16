### Use cases

- Make two or more api calls sequential
- Make two or more api calls in parallel e.g., "A" and "B" API calls
    - Both "A" and "B" calls started as fire and forget. Do not wait for result.
    - "B" API call depends on "A" api call result.
        - Both "A" and "B" calls succeed and return results.
        - When "A" fails then don't wait for "B" call to finish and simply return error state.
        - When "A" fails but let "B" call execute. Return result of "A" and "B".
        - Both "A" and "B" calls fails and return error state.
