all branches:

main
dev
feature1
feature2
feature3
hotfix
documentation


Description of changes:

-update feature1 from dev
-merge feature1 into dev
-rebase feature2 onto dev
-squash commits before merging to dev
-update feature2 branch and merge into dev
-cherry-pick hotfixes to main and dev

Learning Summary:
Differences between merge, rebase, squash, and cherry-pick:

    Merge: when combining two branches, the history of both branches still be kept. This is good to use to merge a feature branch
    into a main branch. But because it keeps everything, the graph of the history will be very complicated

    Rebase: this is like I work on a Feature branch, but during that time Main branch has new code, and rebase will cut the feature branch and put it into the latest commit of Main. This is why we only want to rebase on our personal branch, we don't want to rebase on main branch because that will change the history of main branch and that affects everyone. The main purpose of git rebase is to put the commit graph into linear history which helps devs to understand history better

    Squash: During code we will commit a lot, that would look like a mess because there would be so many commits for every little thing. Squash helps by merge all those small commits into one big commit, that helps the history looks better and clean. Squash will remove all the smaller commits and we then have only 1 new commit

    Cherry-pick: we have code A, B, C in branch feature, and I only need B code. So, I do cherry-pick hash of commit B and bring to main without the need to bringing the whole feature branch to main. So, cherry-pick is very useful for hotfixes. In opposite, if we do git merge, it would bring the all branch to main.

What you observed in the git history for feature1 vs feature2 vs feature3:

    feature1: I can see a branch split and a merge commit in the history. Because I merged feature1 into dev branch so this is very obvious in the history

    feature2: I can see linear history. New commits from feature2 branch attaches to the top of the latest commit of dev

    feature3: I had many small commits. However, after I did git squash, the history is clean with only 1 commit containing all the previous smaller commits. This allows others to follow the history better

When you would use each strategy in real projects:

    Merge: should use to integrate finished code into dev/main.
    Rebase: should use only on private feature branch. This is to make a linear history
    Squash: should use to clean up smaller commits into a larger and meaningful commit. This helps the history to look cleaner and easy to follow
    Cherry-pick: use for hotfixes

For Task3 PR