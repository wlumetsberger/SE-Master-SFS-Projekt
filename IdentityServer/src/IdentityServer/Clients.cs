using IdentityServer4;
using IdentityServer4.Models;
using System.Collections.Generic;

internal class Clients
{
    public static IEnumerable<Client> Get()
    {
        return new List<Client> {
            new Client {
                ClientId = "sfsClient",
                ClientName = "SFS Application",
                AllowedGrantTypes = GrantTypes.Implicit,
                AllowedScopes = new List<string>
                {
                    IdentityServerConstants.StandardScopes.OpenId,
                    IdentityServerConstants.StandardScopes.Profile,
                    IdentityServerConstants.StandardScopes.Email,
                    "role",
                },
                RedirectUris = new List<string> {"https://localhost:44330/signin-oidc"},
                PostLogoutRedirectUris = new List<string> {"https://localhost:44330"}
            }
        };
    }
}