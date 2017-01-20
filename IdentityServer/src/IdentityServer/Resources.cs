using IdentityServer4.Models;
using System.Collections.Generic;

internal class Resources
{
    public static IEnumerable<IdentityResource> GetIdentityResources()
    {
        return new List<IdentityResource> {
            new IdentityResources.OpenId(),
            new IdentityResources.Profile(),
            new IdentityResources.Email(),
            new IdentityResource {
                DisplayName = "Role",
                Description = "Role of the user",
                Name = "role",
                UserClaims = new List<string> {"authorities"}
            }
        };
    }
}